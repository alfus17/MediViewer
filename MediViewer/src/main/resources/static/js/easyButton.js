// buttonFunctions.js
document.addEventListener('DOMContentLoaded', () => {
    const dicomImageElement = document.getElementById('dicomImage');

    // 좌우 반전
    document.getElementById('hFlip').addEventListener('click', function () {
        const viewport = cornerstone.getViewport(dicomImageElement);
        viewport.hflip = !viewport.hflip;
        cornerstone.setViewport(dicomImageElement, viewport);
    });

    // 흑백 반전
    document.getElementById('invert').addEventListener('click', function () {
        let viewport = cornerstone.getViewport(dicomImageElement);
        viewport.invert = !viewport.invert;
        cornerstone.setViewport(dicomImageElement, viewport);
    });

    // 이미지 리셋
    document.getElementById('reset').addEventListener('click', function () {
        cornerstone.reset(dicomImageElement);
    });
});
