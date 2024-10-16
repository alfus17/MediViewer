document.addEventListener('DOMContentLoaded', () => {
    cornerstoneWADOImageLoader.external.cornerstone = cornerstone;
    cornerstoneWADOImageLoader.external.cornerstoneTools = cornerstoneTools;

    // cornerstone 및 DICOM 이미지 초기화
    element = document.getElementById('dicomImage');
    cornerstone.enable(element);

    const imageId = 'wadouri:img/CT.1.2.840.113619.2.55.3.604688252.641.1497007218.50.dcm';

    cornerstone.loadImage(imageId).then(image => {
        cornerstone.displayImage(element, image);
    }).catch(err => {
        console.log('이미지 로드 실패 : ', err);
    });

    // cornerstoneTools 초기화 및 WWWC Tool 추가
    cornerstoneTools.init();
    const WwwcTool = cornerstoneTools.WwwcTool;
    cornerstoneTools.addTool(WwwcTool, {
        configuration: { windowWidth: 400, windowCenter: 40 } // 기본 값 설정 추가
    });

    // WWWC Tool 활성화 버튼
    document.getElementById('wwwcButton').addEventListener('click', () => {
        cornerstoneTools.setToolActive('Wwwc', { mouseButtonMask: 1 });
        console.log("WWWCTool 활성화 - 마우스 드래그로 이미지 밝기/대비를 조정해 보세요.");
    });
});
