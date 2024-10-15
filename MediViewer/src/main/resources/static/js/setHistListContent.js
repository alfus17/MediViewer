// 초기화 공통 로직
function resetHistData() {
	histItems.splice(0, histItems.length);
}

// 환자 데이터 표시
function setHistPData(pID, pName) {
	$("#histPID").text(pID);
	$("#histPName").text(pName);
	
	getHistData(pID);
}

// 환자 이력 데이터 표시 
function getHistData(id) {
	const pID = id;
	
	axios.get('/api/lists/histories', {
		params:{
			pID
		}
	}).then(response => {
		const result = response.data || [];
		if(result.length > 0){
			histItems.push(...result);			
		}
	}).catch(error => {
		console.error(error);
	})
}

// 동적 컨텐츠 로딩으로 인해 함수 선언
function goDetail(studyKey) {
	window.location.href = `/details/${studyKey}`;
}