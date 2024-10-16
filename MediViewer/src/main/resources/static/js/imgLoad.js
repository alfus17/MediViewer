let element;

document.addEventListener('DOMContentLoaded', () => {
    // cornerstone 초기화
    cornerstoneWADOImageLoader.external.cornerstone = cornerstone;
    cornerstoneWADOImageLoader.external.cornerstoneTools = cornerstoneTools;
    
    // 이미지를 넣을 요소 얻어오기
    element = document.getElementById('dicomImage'); // 전역 변수에 할당
    window.element = element; // window에 등록하여 다른 파일에서도 접근 가능

    // 이미지 요소를 초기화
    cornerstone.enable(element);
    
    // 이미지 얻어오기
    const imageId = 'wadouri:img/MR.1.2.392.200036.9116.4.1.6116.40033.5.3001.1.1152393810.dcm';
    
    cornerstone.loadImage(imageId).then(image => {
        cornerstone.displayImage(element, image);
    }).catch(err => {
        console.log('이미지 로드 실패 : ', err);
    });
});
