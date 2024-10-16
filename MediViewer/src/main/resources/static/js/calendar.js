// 포맷 데이터 반환
function formatData(date) {
	const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
}

// 캘린더 객체 생성
flatpickr("#calendarInput", {
			dateFormat: "Y-m-d",
			minDate: "1990-1",
			maxDate: "today",
			mode: "range",
			inline: true,
			onChange: function(conjunction){

				if(conjunction.length === 2){
					const sDate = formatData(conjunction[0]) || '';
					const eDate = formatData(conjunction[1]) || '';
					
					$("#startDate").val(sDate);
					$("#startDate").text(sDate);
					$("#endDate").val(eDate);
					$("#endDate").text(eDate);
				}
			}
		});

// 초기화 로직
function resetCalendar() {
	const fp = document.querySelector("#calendarInput")._flatpickr;
	fp.clear();
	$("#startDate").val('');
	$("#startDate").text('');
	$("#endDate").val('');
	$("#endDate").text('');
}

// 검색 버튼 클릭 시 쿼리 기간 반환
function setStartToEndDate() {
	const sDate = $("#startDate").val();
	const eDate = $("#endDate").val();
	return [sDate, eDate];
}

// 초기화 버튼 이벤트 부여
$("#clearCalendarBtn").click(() => {
	resetCalendar();
})