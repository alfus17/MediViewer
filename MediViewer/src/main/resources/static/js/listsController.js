// 초기화 공통 로직
function resetSelectors(e) {
	$("#queryType").prop("selectedIndex", 0);
	$("#queryString").prop("placeholder", "환자아이디를 입력하세요...");
	$("#queryJudgement").prop("selectedIndex", 0);
	
	
	if($(e.target).is("#resetQuery")){
		resetContentItems();
		resetHistItems();
		getDefList();
	};
	
}

// 기간 선택 후 쿼리 로직
$("#doSelectedDurationQuery").click(() => {
	const date = setStartToEndDate();
	const slice = $("#listPageSize").val();
	
	const params = {
		...setParams(),
		slice,
		sDate: date[0],
		eDate: date[1]
	}
	
	getQueryList(params);
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
	
	getQueryList(params);
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
	
	getQueryList(params);
})

$(document).on('click', 'li[name=listItem]', (e) => {
    const value = $(e.target).closest('li[name=listItem]').attr('value');
    const pID = $(e.target).closest('li[name=listItem]').find('span[name=pID]').text();
    const pName = $(e.target).closest('li[name=listItem]').find('span[name=pName]').text();
    console.log('클릭: ',value, ', ', pID, ', ', pName);
    
    const params = {
		studyKey: value,
		pID,
		pName
	}
    console.log(params);
    getHistoryList(params);
});

$(document).on('dblclick', 'li[name=listItem]', (e) => {
    const value = $(e.target).closest('li[name=listItem]').attr('value');
    console.log('더블클릭: ',value);
});