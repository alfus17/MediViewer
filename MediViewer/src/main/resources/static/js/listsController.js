// 초기화 공통 로직
function resetSelectors(e) {
	$("#queryType").prop("selectedIndex", 0);
	$("#queryString").prop("placeholder", "환자아이디를 입력하세요...");
	$("#queryJudgement").prop("selectedIndex", 0);
	
	if($(e.target).is("#resetQuery")){
		getDefList();
	};
	
	resetHistData();
}

// 기간 선택 후 쿼리 로직
$("#doSelectedDurationQuery").click(() => {
	const date = setStartToEndDate();
	const slice = $("#listPageSize").val();
	
	const param = {
		...setParams(),
		slice,
		sDate: date[0],
		eDate: date[1]
	}
	
	getQueryList(param);
})

// 검색 버튼에 이벤트 부여
$("#doQuery").click(() => {	
	getQueryList(setParams());
})

// 기간 선택 버튼에 이벤트 부여
$("button[name=btnDateDur]").click((e) => {
	const targetDate = $(e.target).val();
	const slice = $("#listPageSize").val();
	const date = setDate(targetDate);
	
	const params = {
		...setParams(),
		slice,
		nowPage,
		sDate: date[0],
		eDate: date[1]
	}
	
	console.log(params);
})

// 초기화 버튼에 이벤트 부여
$("#resetQuery").click((e) => {
	resetSelectors(e);
})

// 페이지 개수 리사이징
$("#listPageSize").change(() => {
	const slice = $("#listPageSize").val();
	const params = {
		...setParams(),
		slice
	}
	
	console.log(params);
})