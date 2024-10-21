
    let element; // 전역 변수
    let imageIds = []; // 전역 변수
    let currentIndex = 0; // 전역 변수
    let isDragging = false; // 드래그 중인지 여부
    let lastMousePosition = { x: 0, y: 0 }; // 마지막 마우스 위치

    // 이미지 표시 함수
    function displayImage(index) {
        if (index < 0 || index >= imageIds.length) {
            return;
        }
        cornerstone.loadImage(imageIds[index]).then(image => {
            cornerstone.displayImage(element, image);
        }).catch(err => {
            console.error("Image load error:", err);
        });
    }

    window.onload = function() {
        element = document.getElementById('dicomImage'); // 전역 변수 초기화
        cornerstone.enable(element);

        // cornerstone-wado-image-loader 설정
        cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

        const urlParams = new URLSearchParams(window.location.search);
        const studyKey = urlParams.get('studykey');
        const seriesKey = urlParams.get('serieskey');
        const apiUrl = `/api/dcmList?studykey=${studyKey}&serieskey=${seriesKey}`;

        // API 요청
        axios.get(apiUrl)
            .then(response => {
                const dcmList = response.data;

                // imageIds 배열 초기화
                imageIds = dcmList.map(dcm => `wadouri:dcm/${dcm.path}/${dcm.fname}`); // path를 사용하여 imageIds 배열 구성

                // 초기 이미지 표시
                displayImage(currentIndex);
            })
            .catch(error => {
                console.error("Error fetching DCM list:", error);
            });

        // 마우스 휠 이벤트로 이미지 변경
        element.addEventListener('wheel', (event) => {
            event.preventDefault(); // 기본 스크롤 동작 방지

            if (event.deltaY > 0) { // 휠을 아래로 스크롤
                if (currentIndex < imageIds.length - 1) {
                    currentIndex++;
                    displayImage(currentIndex);
                }
            } else if (event.deltaY < 0) { // 휠을 위로 스크롤
                if (currentIndex > 0) {
                    currentIndex--;
                    displayImage(currentIndex);
                }
            }
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

