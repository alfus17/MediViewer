const commentButton = document.getElementById('comment');
const commentModal = document.getElementById('commentModal');
const closeModalButton = document.getElementById('closeModal');

commentButton.addEventListener('click', () => {
    commentModal.style.display = 'block';

    // 기존 코멘트를 서버에서 불러오기
    axios.get(`/api/views/comment/${studykey}`)  // URL에 studykey 삽입
        .then(response => {
            const existingComment = response.data.comment;
            if (existingComment) {
                document.getElementById("commentText").value = existingComment;
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
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

// 코멘트 저장
const saveComment = () => {
    const commentText = document.getElementById("commentText").value;

    axios.post('/api/views/saveComment', {
        comment: commentText,
        studykey: studykey  // ReqParams 형식으로 studyKey와 comment를 전송
    })
    .then(response => {
        if (response.data.success) {
            alert("저장되었습니다!");
            document.getElementById("commentText").value = "";  // 저장 후 입력창 초기화
        } else {
            alert("저장에 실패했습니다.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
    });
}

document.getElementById("saveComment").addEventListener("click", saveComment);
