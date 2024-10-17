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
