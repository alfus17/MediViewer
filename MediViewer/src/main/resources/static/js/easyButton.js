// 좌우 반전
 document.getElementById('hFlip').addEventListener('click', function (e) {
        const viewport = cornerstone.getViewport(element);
        viewport.hflip = !viewport.hflip;
        cornerstone.setViewport(element, viewport);
    });

// 흑백 반전
 document.getElementById('invert').addEventListener('click', function (e) {
        let viewport = cornerstone.getViewport(element);
        viewport.invert = !viewport.invert;
        cornerstone.setViewport(element, viewport);
    });
    
// 이미지 리셋
document.getElementById('reset').addEventListener('click', function (e) {
        cornerstone.reset(element);
    });