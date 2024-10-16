// 쿼리 타입 선택 시 항목별로 입력 필드 변경 로직
function setQueryStringContainer() {
    const element = $("#queryType");
    const queryStringContainer = $("#queryStringContainer");
    const value = element.val();
    
    queryStringContainer.empty();
    
    switch(value) {
        case "pID":
            console.log("pid");
            queryStringContainer.append('<input type="text" id="queryString" placeholder="환자아이디를 입력하세요...">');
            break;
        case "pName":
            console.log("pname");
            queryStringContainer.append('<input type="text" id="queryString" placeholder="환자명을 입력하세요...">');
            break;
        case "modality":
			// todo: 장비명 리스트 받아와서 드롭다운으로 변경
            console.log("modality");
            queryStringContainer.append('<input type="text" id="queryString" placeholder="장비명을 입력하세요...">');
            break;
        case "studyDesc":
            console.log("studyDesc");
            queryStringContainer.append('<input type="text" id="queryString" placeholder="검사설명을 입력하세요...">');
            break;
        default:
            queryStringContainer.empty();
            break;
    }
}

// 쿼리 타입 변경 시 적용
$("#queryType").change(setQueryStringContainer);