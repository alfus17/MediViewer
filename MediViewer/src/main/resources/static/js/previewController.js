// dicom 프리뷰
function showPreviewData(studyKey) {
	const element = document.getElementById('previewImage');
	cornerstone.enable(element);
	
	getImgPreview(studyKey, element);
}

// 초기화 공통 로직
function resetHistData() {
	resetItems(histItems);
}

$("#previewOpenSwitch").on('click', () => {
	getPreviewSeries(prevStudyKey);
})