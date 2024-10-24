

// 스택 이미지 출력
function displayImage(index) {
    if (index < 0 || index >= imageIds.length) {
        return;
    }
    console.log("Loading image URL:", imageIds[index]); // 로드할 URL 로그 출력
    cornerstone.loadImage(imageIds[index]).then(image => {
        cornerstone.displayImage(element, image);
        
        // 동적으로 스타일 적용
        element.style.width = '100%';  // 이미지의 너비를 100%로 설정
        element.style.height = '100%';  // 높이는 비율에 맞게 자동 조정
        
    }).catch(err => {
        console.error("Image load error:", err);
    });
}

// 썸네일 이미지 하나
function loadDicomThumbnail(imageId, divId) {
    const dicomDiv = document.getElementById(divId);
    if (!dicomDiv) {
        return;
    }
    cornerstone.enable(dicomDiv); // 썸네일을 로드하기 전에 해당 div를 활성화합니다.
	
	//썸네일 이미지 경로 넣기
    cornerstone.loadImage(imageId).then(image => {
        cornerstone.displayImage(dicomDiv, image);
    }).catch(err => {
        console.error("Thumbnail load error:", err);
    });
}

// 썸네일 한번에 로드
function loadSeriesThumbnails(seriesList) {
    const container = document.getElementById('thumbnailContainer'); // 썸네일 컨테이너
	console.log("loadSeriesThumbnails _seriesList " , seriesList)
// 썸네일 스타일 추가
    seriesList.forEach((imageArray, i) => {
        const dicomDiv = document.createElement('div');
        dicomDiv.id = `dicomImage_${i}`;
        dicomDiv.style.width = '100px';
        dicomDiv.style.height = '100px';
        dicomDiv.style.margin = '10px';
        dicomDiv.style.cursor = 'pointer';
        container.appendChild(dicomDiv); // 컨테이너에 추가

        if (imageArray.length > 0) {
            const imageId = imageArray[0]; // 각 시리즈의 첫 번째 이미지
            loadDicomThumbnail(imageId, dicomDiv.id); // 이미지를 해당 div에 로드
            
            // 썸네일 클릭 이벤트 추가
            dicomDiv.addEventListener('click', () => {
                currentIndex = i; // 현재 인덱스를 클릭한 썸네일의 인덱스로 설정
                currentImageIndex = 0; // 첫 번째 이미지로 초기화
                imageIds = seriesList[currentIndex]; // 선택한 시리즈의 이미지 ID로 업데이트
                displayImage(currentImageIndex); // 이미지 표시
            });
        }
    });
}

// 로드 될때
window.onload = function() {
	// 스택이미지 id
    element = document.getElementById('dicomImage'); // 전역 변수 초기화
    cornerstone.enable(element); // 요소를 활성화

    // cornerstone-wado-image-loader 설정
    cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

    const urlPath = window.location.pathname; // 현재 경로 가져오기
    const pathSegments = urlPath.split('/'); // 경로를 '/'로 분리
    const studykey = pathSegments[pathSegments.indexOf('view') + 1]; 
    console.log("studyKey:", studykey);

    // API 요청
    axios.get(`/api/views/${studykey}`).then(result => {
        console.log(result.data);
        series.push(...result.data); // 시리즈 배열에 결과 추가

        const urlList = series.map(serieskey => `/api/views/${studykey}/${serieskey}`);
        
        axios.all(urlList.map(url => axios.get(url))).then(axios.spread((...responses) => {
            const dataList = responses.map(response => response.data);
            // 이미지배열 이름 => dcmPathLists
            dcmPathLists.push(...dataList);
            console.log('dcmPathLists',dcmPathLists);
            
            // dcmList에 응답 데이터 할당
            dcmList.imageFileNames = dcmPathLists; 
			// 썸네일 로드 함수 호출
            loadSeriesThumbnails(dcmList.imageFileNames);
        	console.log("onLoad _ 썸네일 로드 완료  " ) 

        	// 초기 이미지 로드
            if (dcmList.imageFileNames.length > 0) {
                currentIndex = 0; // 첫 번째 시리즈 선택
                imageIds = dcmList.imageFileNames[currentIndex]; // 첫 번째 시리즈의 이미지 ID로 업데이트
                displayImage(currentImageIndex); // 첫 번째 이미지 표시
            }

        })).catch(errors => {
            console.error("Error in axios.all: ", errors);
        });
    });

    // 마우스 휠 이벤트로 이미지 변경
	element.addEventListener('wheel', (event) => {
    event.preventDefault(); // 기본 스크롤 동작 방지

    if (imageIds.length === 0) return; // 이미지가 없으면 아무것도 하지 않음

    if (event.deltaY > 0) { // 휠을 아래로 스크롤
        if (currentImageIndex < imageIds.length - 1) {
            currentImageIndex++; // 현재 시리즈의 다음 이미지로 이동
        }
    } else if (event.deltaY < 0) { // 휠을 위로 스크롤
        if (currentImageIndex > 0) {
            currentImageIndex--; // 현재 시리즈의 이전 이미지로 이동
        }
    }
    
    displayImage(currentImageIndex); // 현재 인덱스에 해당하는 이미지 표시
});


    // 이미지 이동 기능 추가
    element.addEventListener('mousedown', (event) => {
        if (event.button === 0) { // 왼쪽 버튼만 작동
            isDragging = true;
            lastMousePosition = { x: event.clientX, y: event.clientY };
        }
    });

    element.addEventListener('mousemove', (event) => {
        if (isDragging) {
            const deltaX = event.clientX - lastMousePosition.x;
            const deltaY = event.clientY - lastMousePosition.y;

            const viewport = cornerstone.getViewport(element);
            viewport.translation.x += deltaX;
            viewport.translation.y += deltaY;
            cornerstone.setViewport(element, viewport);

            lastMousePosition = { x: event.clientX, y: event.clientY }; // 현재 위치 업데이트
        }
    });

    element.addEventListener('mouseup', () => {
        isDragging = false;
    });

    element.addEventListener('mouseleave', () => {
        isDragging = false;
    });
};
