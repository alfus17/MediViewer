document.addEventListener('DOMContentLoaded', () => {
    const dicomImageElement = document.getElementById('dicomImage');
    let isZoomActive = false; // 줌 기능 활성화 여부
    let isDragging = false; // 드래그 중인지 여부
    let startY = 0; // 드래그 시작 Y 좌표 저장

    document.getElementById('zoom').addEventListener('click', function () {
        isZoomActive = !isZoomActive; // 버튼을 누를 때마다 줌 활성화/비활성화 전환
        if (isZoomActive) {
            console.log("줌 기능 활성화");
        } else {
            console.log("줌 기능 비활성화");
        }
    });

    // 우클릭(컨텍스트 메뉴) 방지 및 줌 활성화 시 드래그 동작 감지
    dicomImageElement.addEventListener('contextmenu', (event) => {
        event.preventDefault();
    });

    dicomImageElement.addEventListener('mousedown', (event) => {
        if (isZoomActive && event.button === 2) { // 줌이 활성화된 상태에서 우클릭이면
            isDragging = true;
            startY = event.clientY;
        }
    });

    dicomImageElement.addEventListener('mousemove', (event) => {
        if (isZoomActive && isDragging) {
            const currentY = event.clientY;
            const deltaY = startY - currentY;

            const viewport = cornerstone.getViewport(dicomImageElement);
            viewport.scale += deltaY * 0.01; // deltaY에 따라 줌 조절
            cornerstone.setViewport(dicomImageElement, viewport);

            startY = currentY; // 현재 위치를 다시 시작 위치로 설정
        }
    });

    dicomImageElement.addEventListener('mouseup', () => {
        isDragging = false;
    });

    dicomImageElement.addEventListener('mouseleave', () => {
        isDragging = false;
    });
});
