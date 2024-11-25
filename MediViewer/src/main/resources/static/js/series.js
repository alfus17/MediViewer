let isSeriesViewActive = false;
let currentPage = 0; // 현재 페이지를 저장할 변수
const itemsPerPage = 16; // 페이지당 최대 이미지 수
const container = document.getElementById('seriesImage');
const dicomImageContainer = document.getElementById('dicomImage');
const originalContent = container.innerHTML; // 초기 상태 저장

// 다중 뷰포트를 생성하고 초기화하는 함수
function initializeViewports(rows, cols, seriesList) {
    container.innerHTML = ''; // 기존 뷰포트 초기화
    container.style.display = 'grid';
    container.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;
    container.style.gridTemplateRows = `repeat(${rows}, 1fr)`;
    
    container.style.gap = '10px'; // 이미지들 사이에 10px 간격 추가

    // 현재 페이지에 해당하는 seriesList 항목만 slice로 가져오기
    const start = currentPage * itemsPerPage;
    const end = start + itemsPerPage;
    const pagedSeriesList = seriesList.slice(start, end);

    pagedSeriesList.forEach((series, index) => {
        const dicomDiv = document.createElement('div');  // dicomDiv를 새로 생성
        dicomDiv.id = `dicomImage_${index}`;
        dicomDiv.className = 'dicomImage';
        dicomDiv.style.width = '100%';
        dicomDiv.style.height = '100%';

        // 썸네일 이미지 로드
        loadSeriesImage(series, dicomDiv);

        container.appendChild(dicomDiv);

        cornerstone.enable(dicomDiv); // cornerstone 활성화
    });

    addNavigationButtons(seriesList, rows, cols); // 네비게이션 버튼 다시 추가
}

// 각 시리즈의 첫 번째 이미지를 로드하는 함수
function loadSeriesImage(series, dicomDiv) {
    const imageId = series[0]; // 첫 번째 이미지를 로드 (시리즈의 첫 번째 이미지)
    
   cornerstone.loadImage(imageId).then(image => {
    cornerstone.displayImage(dicomDiv, image); // 이미지 표시

    const viewport = cornerstone.getDefaultViewportForImage(dicomDiv, image);
    viewport.scale = calculateSeriesScale(image, dicomDiv); // 새로 변경된 함수 이름 사용
    cornerstone.setViewport(dicomDiv, viewport);
}).catch(err => {
    console.error("Error loading series image:", err);
});
}

// 시리즈용 스케일 계산
function calculateSeriesScale(image, div) {
    const canvasWidth = div.clientWidth;
    const canvasHeight = div.clientHeight;
    return Math.min(canvasWidth / image.width, canvasHeight / image.height);
}

// 이전 페이지로 이동
function prevPage(seriesList, rows, cols) {
    if (currentPage > 0) {
        currentPage--;
        initializeViewports(rows, cols, seriesList);
    }
}

// 다음 페이지로 이동
function nextPage(seriesList, rows, cols) {
    const maxPage = Math.ceil(seriesList.length / itemsPerPage) - 1;
    if (currentPage < maxPage) {
        currentPage++;
        initializeViewports(rows, cols, seriesList);
    }
}

// 네비게이션 버튼 추가 함수
function addNavigationButtons(seriesList, rows, cols) {
    const prevButton = document.createElement('button');
    prevButton.innerText = '◀';
    prevButton.style.position = 'absolute';
    prevButton.style.left = '240px';
    prevButton.style.top = '520px';
    prevButton.onclick = () => prevPage(seriesList, rows, cols);
    container.appendChild(prevButton);

    const nextButton = document.createElement('button');
    nextButton.innerText = '▶';
    nextButton.style.position = 'absolute';
    nextButton.style.right = '230px';
    nextButton.style.top = '520px';
    nextButton.onclick = () => nextPage(seriesList, rows, cols);
    container.appendChild(nextButton);
}

// 시리즈 버튼 클릭 시 다중 뷰포트 초기화 및 초기 화면 복원
document.getElementById('series').addEventListener('click', () => {
    const thumbnailContainer = document.getElementById('thumbnailContainer');
    const body = document.body; // body 요소 선택

    if (isSeriesViewActive) {
        // 시리즈 뷰 비활성화: 썸네일 보이기, seriesImage 숨기기
        dicomImageContainer.style.display = 'block';
        container.style.display = 'none';
        thumbnailContainer.style.display = 'block'; // 썸네일 보이기
        body.classList.remove('series-view-active'); // 클래스 제거
    } else {
        // 시리즈 뷰 활성화: 썸네일 숨기기, seriesImage 확장
        dicomImageContainer.style.display = 'none';
        container.style.display = 'block';
        thumbnailContainer.style.display = 'none'; // 썸네일 숨기기
        body.classList.add('series-view-active'); // 클래스 추가

        // 시리즈 뷰포트 초기화
        initializeViewports(4, 4, dcmList.imageFileNames);
    }

    isSeriesViewActive = !isSeriesViewActive;
});
