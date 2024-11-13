const toastMessage = '이미지 영역 혹은 우측 하단 축소 버튼을 클릭하면 창이 닫힙니다.';

function showToast(target) {
	target.addClass("active");
	target.text(toastMessage);
	setTimeout(() => {
		target.removeClass("active");
	}, 4000)
}

// 모달 활성화 여부 스위치 로직
function switchCalendarStatus() {
	isCalendarOpen = !isCalendarOpen;
}

function switchPreviewStatus() {
	isPreviewOpen = !isPreviewOpen;
}

// 모달 표시 여부 관리 로직
function setView(target, b) {
	b ? target.show() : target.hide();
}

// 캘린더 모달 제어 이벤트 부여
$("#calendarOpenSwitch, #doSelectedDurationQuery").click(() => {
	switchCalendarStatus();
	setView($(".aside_modal_area"),isCalendarOpen);
})

$("#previewOpenSwitch, #previewCloseSwitch, #prevImageArea").click(() => {
	switchPreviewStatus();
	setView($(".preview_modal_area"),isPreviewOpen);
	if(isPreviewOpen){
		const toast = $("#toast");
		showToast(toast);
	}
})