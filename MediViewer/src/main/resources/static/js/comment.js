const commentButton = document.getElementById('comment');
const commentModal = document.getElementById('commentModal');
const closeModalButton = document.getElementById('closeModal');

// 코멘트 버튼 클릭 시 모달 표시
commentButton.addEventListener('click', () => {
    commentModal.style.display = 'block';
});

// 모달 닫기 버튼 클릭 시 모달 숨기기
closeModalButton.addEventListener('click', () => {
    commentModal.style.display = 'none';
});

// 모달 바깥 영역 클릭 시 모달 숨기기
window.addEventListener('click', (event) => {
    if (event.target === commentModal) {
        commentModal.style.display = 'none';
    }
});

document.getElementById('saveComment').addEventListener('click', () => {
    const commentText = document.getElementById('commentText').value;

    const saveComment = (nowPage) => {
        axios.post(`/api/views/save`, { comment: commentText, page: nowPage })
            .then(response => {
                if (response.data.success) {
                    alert('코멘트가 성공적으로 저장되었습니다.');
                    document.getElementById('commentText').value = ''; // 입력 필드 초기화
                } else {
                    alert('코멘트 저장에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error("Error saving comment:", error);
                alert('저장 중 오류가 발생했습니다.');
            });
    };

    // 현재 페이지나 관련 정보를 매개변수로 전달하여 saveComment 호출
    saveComment(1); // 예시: 현재 페이지 번호 1로 설정
});