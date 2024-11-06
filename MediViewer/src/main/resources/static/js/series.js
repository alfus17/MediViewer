let isSeriesViewActive = false;
let currentPage = 0; // 현재 페이지를 저장할 변수
const itemsPerPage = 16; // 페이지당 최대 이미지 수
const container = document.getElementById('dicomImage');
const originalContent = container.innerHTML; // 초기 상태 저장

// 다중 뷰포트를 생성하고 초기화하는 함수
function initializeViewports(rows, cols, seriesList) {
    container.innerHTML = ''; // 기존 뷰포트 초기화
    container.style.display = 'grid';
    container.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;
    container.style.gridTemplateRows = `repeat(${rows}, 1fr)`;

    // 현재 페이지에 해당하는 seriesList 항목만 slice로 가져오기
    const start = currentPage * itemsPerPage;
    const end = start + itemsPerPage;
    const pagedSeriesList = seriesList.slice(start, end);

    pagedSeriesList.forEach((series, index) => {
        const dicomDiv = document.createElement('div');
        dicomDiv.id = `dicomImage_${index}`;
        dicomDiv.className = 'dicomImage';
        dicomDiv.style.width = '100%';
        dicomDiv.style.height = '100%';

        // 썸네일 이미지 로드
        loadSeriesImage(series, dicomDiv);

        container.appendChild(dicomDiv);

        cornerstone.enable(dicomDiv); // cornerstone 활성화
    });

    addNavigationButtons(container, seriesList, rows, cols); // 네비게이션 버튼 다시 추가
}

// 각 시리즈의 첫 번째 이미지를 로드하는 함수
function loadSeriesImage(series, dicomDiv) {
    const imageId = series[0]; // 첫 번째 이미지를 로드 (시리즈의 첫 번째 이미지)
    
    cornerstone.loadImage(imageId).then(image => {
        cornerstone.displayImage(dicomDiv, image); // 이미지 표시

        const viewport = cornerstone.getDefaultViewportForImage(dicomDiv, image);
        viewport.scale = calculateScale(image); // 이미지 크기 조정
        cornerstone.setViewport(dicomDiv, viewport);
    }).catch(err => {
        console.error("Error loading series image:", err);
    });
}

// 캔버스에 맞는 스케일 계산
function calculateScale(image) {
    const canvasWidth = container.clientWidth;
    const canvasHeight = container.clientHeight;
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
function addNavigationButtons(container, seriesList, rows, cols) {
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
    nextButton.style.right = '40px';
    nextButton.style.top = '520px';
    nextButton.onclick = () => nextPage(seriesList, rows, cols);
    container.appendChild(nextButton);
}

// 시리즈 버튼 클릭 시 다중 뷰포트 초기화 및 초기 화면 복원
document.getElementById('series').addEventListener('click', () => {
    const rows = 4;
    const cols = 4;
    const seriesList = dcmList.imageFileNames; // 시리즈 이미지 ID 리스트

    if (isSeriesViewActive) {
        // 시리즈 뷰 비활성화: 초기 상태로 복원
        container.innerHTML = originalContent; // 초기 HTML 콘텐츠로 복원
        container.style.display = ''; // grid 스타일 해제
        displayImage(currentIndex);
    } else {
        currentPage = 0; // 초기 페이지로 설정
        initializeViewports(rows, cols, seriesList);
         // 다중 뷰포트 초기화
    }

    isSeriesViewActive = !isSeriesViewActive; // 상태 반전
});
