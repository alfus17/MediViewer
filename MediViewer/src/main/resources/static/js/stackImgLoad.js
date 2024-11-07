

// 스택 이미지 출력 함수
function displayImage(index) {
    if (index < 0 || index >= imageIds.length) {
        return;
    }
    console.log("Loading image URL:", imageIds[index]);
    cornerstone.loadImage(imageIds[index]).then(image => {
        cornerstone.displayImage(element, image);

        // 이미지가 캔버스에 꽉 차게 설정
        const viewport = cornerstone.getDefaultViewportForImage(element, image);
        viewport.scale = calculateScale(image);
        cornerstone.setViewport(element, viewport);
        
        displayImageInfo(index); // 이미지 정보 텍스트 표시
    }).catch(err => {
        console.error("Image load error:", err);
    });
}

// 캔버스에 맞는 스케일 계산
function calculateScale(image) {
    const canvasWidth = element.clientWidth;
    const canvasHeight = element.clientHeight;
    return Math.min(canvasWidth / image.width, canvasHeight / image.height);
}

// 이미지 정보 표시
function displayImageInfo(index) {
    // 기존 imgInfo 제거
    const existingInfo = document.getElementById('imgInfo');
    if (existingInfo) {
        element.removeChild(existingInfo);
    }

    // 새 imgInfo 생성
    const imgInfo = document.createElement('div');
    imgInfo.id = 'imgInfo';
    imgInfo.style.position = 'absolute';
    imgInfo.style.top = '200px';
    imgInfo.style.left = '300px';
    imgInfo.style.fontSize = '30px';
    imgInfo.style.color = 'white';
    imgInfo.innerHTML = `series ${currentIndex + 1}<br>${index + 1} / ${imageIds.length}`;

    // imgInfo 추가
    element.appendChild(imgInfo);
}

// 썸네일 이미지 로드
function loadDicomThumbnail(imageId, divId) {
    const dicomDiv = document.getElementById(divId);
    if (!dicomDiv) return;

    cornerstone.enable(dicomDiv);
    cornerstone.loadImage(imageId).then(image => {
        cornerstone.displayImage(dicomDiv, image);
    }).catch(err => {
        console.error("Thumbnail load error:", err);
    });
}

// 썸네일 여러 개 로드
function loadSeriesThumbnails(seriesList) {
    const container = document.getElementById('thumbnailContainer');
    container.innerHTML = ''; // 기존 썸네일 초기화

    seriesList.forEach((imageArray, i) => {
        const dicomDiv = document.createElement('div');
        dicomDiv.id = `dicomImage_${i}`;
        dicomDiv.style.width = '100%';
        dicomDiv.style.height = '150px';
        dicomDiv.style.marginTop = '10px';
        dicomDiv.style.cursor = 'pointer';
        dicomDiv.style.position = 'relative';
        if(i == 0){
			 dicomDiv.style.border = "10px solid";
		}

        const thumbnailNumber = document.createElement('div');
        thumbnailNumber.style.position = 'absolute';
        thumbnailNumber.style.top = '12px';
        thumbnailNumber.style.left = '12px';
        thumbnailNumber.style.color = 'white';
        thumbnailNumber.style.fontSize = '16px';
        thumbnailNumber.innerText = `${i + 1}. 썸네일`;

        dicomDiv.appendChild(thumbnailNumber);
        container.appendChild(dicomDiv);

        if (imageArray.length > 0) {
            loadDicomThumbnail(imageArray[0], dicomDiv.id); // 썸네일 로드

            dicomDiv.addEventListener('click', () => {
                // 클릭된 썸네일의 시리즈로 이동
                
                if(currentIndex != i ){
					document.getElementById(`dicomImage_${currentIndex}`).style.border = '';
				}
                
                currentIndex = i; // 클릭된 썸네일의 시리즈 인덱스 설정
                currentImageIndex = 0; // 시리즈 내 첫 번째 이미지로 이동
                imageIds = seriesList[currentIndex]; // 해당 시리즈의 이미지 ID 설정
                displayImage(currentImageIndex); // 이미지 표시
                dicomDiv.style.border = "10px solid";
            });
        }
    });
}

// 초기 이미지 로드 함수
function loadInitialImage() {
    if (dcmList.imageFileNames.length > 0) {
        currentIndex = 0;
        currentImageIndex = 0;
        imageIds = dcmList.imageFileNames[currentIndex];
        displayImage(currentImageIndex);
    }
}

// 이미지 데이터를 요청하는 함수
function fetchData(studykey, callback) {
    axios.get(`/api/views/${studykey}`).then(result => {
        series.push(...result.data.series);
        const urlList = series.map(serieskey => `/api/views/${studykey}/${serieskey}`);
       
        axios.all(urlList.map(url => axios.get(url))).then(axios.spread((...responses) => {
            const dataList = responses.map(response => response.data);
            dcmList.imageFileNames = dataList;
            
            // 데이터 로드 완료 후 콜백 호출
            if (callback) callback();
            
        })).catch(errors => {
            console.error("Error in axios.all: ", errors);
        });
    });
}

// start 함수에서 fetchData 호출 후 콜백으로 후속 작업 연결
function start() {
    element = document.getElementById('dicomImage');
    cornerstone.enable(element);

    cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

    const urlPath = window.location.pathname;
    const studykey = urlPath.split('/').slice(-1)[0];
    console.log("studyKey:", studykey);

    // 데이터 로드 후 썸네일과 초기 이미지를 로드하도록 fetchData에 콜백 추가
    fetchData(studykey, () => {
        loadSeriesThumbnails(dcmList.imageFileNames);
        loadInitialImage();
    });
}

// 마우스 이벤트 핸들러
function registerMouseEvents() {
    element.addEventListener('wheel', (event) => {
        event.preventDefault();
        if (imageIds.length === 0) return;

        if (event.deltaY < 0 && currentImageIndex < imageIds.length - 1) {
            currentImageIndex++;
        } else if (event.deltaY > 0 && currentImageIndex > 0) {
            currentImageIndex--;
        }
        
        displayImage(currentImageIndex);
    });

    element.addEventListener('mousedown', (event) => {
        if (event.button === 0) {
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

            lastMousePosition = { x: event.clientX, y: event.clientY };
        }
    });

    element.addEventListener('mouseup', () => {
        isDragging = false;
    });

    element.addEventListener('mouseleave', () => {
        isDragging = false;
    });
}
