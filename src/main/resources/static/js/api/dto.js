function submitResearchDTO(endpoint) {
    updateResearchDTO('submit')
    // 유효성 검사 수행

    // 제목과 내용 유효성 검사
    const titleInput = document.getElementById('research-title-input');
    const contentInput = document.getElementById('research-content-input');
    const suriForms = document.querySelectorAll('.suri-form');
    const startDateInput = document.getElementById('research-startdate-input');
    const endDateInput = document.getElementById('research-enddate-input');

    if (!validateForms(titleInput, contentInput, suriForms)) {
        return; // 유효성 검사 실패 시 중단
    }


    if (!validateDateInput(startDateInput, endDateInput)) {
        return; // 유효성 검사 실패 시 중단
    }

    // rsDTO 객체를 JSON 형식으로 변환
    const requestData = JSON.stringify(rsDTO);

    // Axios 사용하여 POST 요청 전송
    /* global axios */
    axios.post(endpoint, requestData, {
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(function () {
            // 성공적으로 서버에서 응답을 받은 경우
            alert('데이터가 성공적으로 전송되었습니다.');
            if (endpoint === '/research/create') {
                localStorage.removeItem('rsDTO');
            }

            window.location.href = '/research/list';
        })
        .catch(function (error) {
            // 오류 발생 시 처리
            console.error('데이터 전송 중 오류가 발생했습니다.', error);
            alert('데이터 전송 중 오류가 발생했습니다.')
        });
}

function updateResearchDTO(flag) {
    const suriForms = document.querySelectorAll('.suri-form');
    const updatedSuriData = [];
    suriForms.forEach((form, index) => {
        const questionNumber = index + 1;
        const suriData = {
            sur_seq: rsDTO.sur_seq,
            suri_seq: form.querySelector(`input[name^="suri_seq_"]`).value,
            suri_no: form.querySelector(`input[name^="suri_no_"]`).value,
            suri_title: form.querySelector(`input[name^="suri_title_"]`).value,
            suri_que1: form.querySelector(`input[name^="suri_que1_"]`).value,
            suri_que2: form.querySelector(`input[name^="suri_que2_"]`).value,
            suri_que3: form.querySelector(`input[name^="suri_que3_"]`).value,
            suri_que4: form.querySelector(`input[name^="suri_que4_"]`).value,
            suri_que5: form.querySelector(`input[name^="suri_que5_"]`).value,
            suri_multi: form.querySelector(`input[name^="suri_multi_${questionNumber}"]`).value,
            suri_etc: form.querySelector(`input[name^="suri_etc_${questionNumber}"]:checked`).value,
        };
        updatedSuriData.push(suriData);
    });

    // rs 업데이트
    rsDTO.suri = updatedSuriData;

    // research-title-input
    rsDTO.sur_title = document.getElementById('research-title-input').value;
    // research-content-input
    rsDTO.sur_desc = document.getElementById('research-content-input').value;
    // research-startdate-input
    rsDTO.sur_sat_date = document.getElementById('research-startdate-input').value;
    // research-enddate-input
    rsDTO.sur_end_date = document.getElementById('research-enddate-input').value;

    localStorage.setItem('rsDTO', JSON.stringify(rsDTO));


    // 확인 메시지 또는 다른 작업을 추가할 수 있습니다.
    if (flag === 'submit') {
        // submitResearchDTO()
    } else {
        alert('중간 저장되었습니다.');
    }
    console.log(rsDTO);
}