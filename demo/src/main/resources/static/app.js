document.addEventListener('DOMContentLoaded', () => {
	// cornerston초기화
	cornerstoneWADOImageLoader.external.cornerstone = cornerstone;
	cornerstoneWADOImageLoader.external.cornerstoneTools = cornerstoneTools;
	
	// 이미지를 넣을 요소 얻어오기
	const element = document.getElementById('dicomImage');
	
	// 이미지 요소를 초기화
	cornerstone.enable(element);
	
	const imgpath = '/dicome/img/';
	
	console.log("imgpath :",imgpath );
	
	console.log("envImgPath : ", envImgPath);
	const imageId = `wadouri:/dicome/img/PR.1.2.410.200119.10101001.30028.20221227162225931335.1000.1.dcm`;
	console.log("imageId : ", imageId);
	// 이미지 얻어오기
	//const imageId = 'wadouri:img/MR.1.2.392.200036.9116.4.1.6116.40033.5.3001.1.1152393810.dcm';
	
	cornerstone.loadImage(imageId).then(image => {
		cornerstone.displayImage(element, image);
	}).catch(err => {
		console.log('이미지 로드 실패 : ', err);
	});
});
/*
document.addEventListener("DOMContentLoaded", function() {
    // HTML에서 정의한 appTitle 변수를 여기서 사용할 수 있음
    console.log("App Title from Thymeleaf: ", window.envImgPath);

  
});*/
