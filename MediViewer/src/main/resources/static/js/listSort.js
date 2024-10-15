// 헤더 기준 정렬 로직
function sortList(sortItems, id, order) {
	sortItems.sort((a, b) => {
		let fieldA = a[id];
		let fieldB = b[id];
		
		if(typeof fieldA === 'string') {
			fieldA = fieldA.toLowerCase();
			fieldB = fieldB.toLowerCase();
		}
		
		if(fieldA < fieldB) {
			return -1 * order;
		}
		if(fieldA < fieldB) {
			return 1 * order;
		}
		return 0;
	})
}

// 헤더에 클릭 시 정렬 이벤트 부여
$("span[name=itemHeader]").click(() => {
	const id = $(this).attr("id");
	
	if(id.startsWith('hist')){
		const column = id.substring(4, 5).toLowerCase() + id.substring(5);
		if(sortOrder[2] !== column){
			sortOrder[2] = column;
			sortOrder[3] = 1;
		} else {
			sortOrder[3] *= -1;
		}
		sortList(histItems, sortOrder[2], sortOrder[3]);
	} else {
		if(sortOrder[0] !== id){
			sortOrder[0] = id;
			sortOrder[1] = 1;
		} else {
			sortOrder[1] *= -1;
		}
		sortList(items, sortOrder[0], sortOrder[1]);
	}
})