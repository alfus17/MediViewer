// 초기화 공통 로직
function resetSelectors() {
	resetQueryStrings();
	resetContentItems();
	resetHistItems();
	getQueryList(setDefParams());
	nowPage = 1;
}

// 기간 선택 후 쿼리 로직
$("#doSelectedDurationQuery").click(() => {
	const date = setStartToEndDate();
	const slice = parseInt($("#slice").val(), 10);
	nowPage = 1;
	
	const params = {
		...setParams(),
		slice,
		sdate: date[0],
		edate: date[1],
		nowPage
	}
	
	getQueryList(params);
})

// 검색 버튼에 이벤트 부여
$("#doQuery").click(() => {
	const date = setStartToEndDate();
	const slice = parseInt($("#slice").val(), 10);
	nowPage = 1;
	
	const params = {
		...setParams(),
		slice,
		sdate: date[0],
		edate: date[1],
		nowPage
	}
	
	getQueryList(params);
})

// 기간 선택 버튼에 이벤트 부여
$("button[name=btnDateDur]").click((e) => {
	const targetDate = $(e.target).val();
	const slice = parseInt($("#slice").val(), 10);
	const date = setDate(targetDate);
	nowPage = 1;
	
	const params = {
		...setParams(),
		slice,
		nowPage,
		sdate: date[0],
		edate: date[1]
	}
	
	getQueryList(params);
})

// 초기화 버튼에 이벤트 부여
$("#resetQuery").click((e) => {
	resetSelectors(e);
})

// 페이지 개수 리사이징
$("#slice").change(() => {
	const slice = $("#slice").val();
	nowPage = 1;
	
	const params = {
		...setParams(),
		slice,
		nowPage
	}
	
	getQueryList(params);
})

// 페이지 더보기
$("button[name=getMoreDefList]").click(() => {
	nowPage += 1;
	
	getDefList(nowPage);
})

$("button[name=getMoreQueryList]").click(() => {
	nowPage += 1;
	
	const params = {
		...setDefParams(),
		nowPage
	};
	
	getQueryList(params);
})

// 리스트 항목 선택
$(document).on('click', 'li[name=listItem]', (e) => {
    prevStudyKey = $(e.target).closest('li[name=listItem]').attr('value');
    const pID = $(e.target).closest('li[name=listItem]').find('span[name=pID]').text();
    const pName = $(e.target).closest('li[name=listItem]').find('span[name=pName]').text();
    console.log($(e.target).closest('li[name=listItem]'));
    console.log('클릭: ',prevStudyKey, ', ', pID, ', ', pName);
    
    const params = {
		studyKey: prevStudyKey,
		pid: pID,
		pname: pName
	}
    console.log(params);
    
    console.log('doGetHistoryList');
    getHistoryList(params);
    
    console.log('doShowPreviewData');
    showPreviewData(prevStudyKey);
});

// 리스트 항목 선택
$(document).on('click', 'li[name=histListItem]', (e) => {
    prevStudyKey = $(e.target).closest('li[name=histListItem]').attr('value');
    console.log($(e.target).closest('li[name=histListItem]'));
    
    const params = {
		studyKey: prevStudyKey,
		pid: pID,
		pname: pName
	}
    console.log(params);
    
    console.log('doShowPreviewData');
    showPreviewData(prevStudyKey);
});

// 리스트 항목 상세보기
$(document).on('dblclick', 'li[name=listItem]', (e) => {
    const value = $(e.target).closest('li[name=listItem]').attr('value');
    console.log('더블클릭: ',value);
    window.location.href = `/view/${value}`;
});

// 리스트 항목 상세보기
$(document).on('dblclick', 'li[name=histListItem]', (e) => {
    const value = $(e.target).closest('li[name=histListItem]').attr('value');
    console.log('더블클릭: ',value);
    window.location.href = `/view/${value}`;
});

//로그아웃
$("#logout").click(() => {
	window.location.href = '/logout';
})