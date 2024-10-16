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

// axios 공통 로직
function setItems(response) {
	const data = response.data;
	items.splice(0, items.length);
	if(data.length > 0){
		items.push(...data)
	}
}

// 기간 선택 후 쿼리 로직
$("#doSelectedDurationQuery").click(() => {
	const [sDate, eDate] = setStartToEndDate();
	const slice = $("#listPageSize").val();
	
	axios.get('/api/lists/duration', {
		params: {
			sDate,
			eDate,
			slice
		}
	}).then(response => {
		setItems(response);
	}).catch(error => {
		console.error(error);
	})
})

// 검색 버튼에 이벤트 부여
$("#doQuery").click(() => {
	const type = $("#queryType").val();
	const query = $("#queryString").val();
	const state = $("#queryJudgement").val();
	const slice = $("#listPageSize").val();
	console.log(type + ": " + query + ", " + state);
	
	axios.get('/api/lists/query', {
		params: {
			type,
			query,
			state,
			slice
		}
	}).then(response => {
		setItems(response);
	}).catch(error => {
		console.error(error);
	})
})

// 기간 선택 버튼에 이벤트 부여
$("button[name=btnDateDur]").click((e) => {
	resetSelectors(e);
	const targetDate = $(e.target).val();
	const slice = $("#listPageSize").val();
	console.log(targetDate);
	
	axios.get('/api/lists/days', {
		params: {
			targetDate,
			slice
		}
	}).then(response => {
		setItems(response);
	}).catch(error => {
		console.error(error);
	})
})

// 초기화 버튼에 이벤트 부여
$("#resetQuery").click((e) => {
	resetSelectors(e);
})

// 페이지 개수 리사이징
$("#listPageSize").change(() => {
	const slice = $("#listPageSize").val();
	console.log(slice);
	
	axios.get('/api/lists/resize', {
		params: {
			slice
		}
	}).then(response => {
		setItems(response);
	}).catch(error => {
		console.error(error);
	})
})