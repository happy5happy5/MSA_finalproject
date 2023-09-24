function validateInput(input, maxLength) {
    const helpElement = document.getElementById(input.id + '-help');
    const inputLength = input.value.length;
    helpElement.textContent = `${inputLength} / ${maxLength} 자`;
    if (inputLength > maxLength) {
        helpElement.classList.add('text-danger');
        input.classList.add('is-invalid');
        // 1초 뒤에 maxLength 길이로 자르기
        helpElement.textContent = `${inputLength} / ${maxLength} 자` + `  최대 ${maxLength}자까지 입력 가능합니다.`;
        setTimeout(() => {
            input.value = input.value.substring(0, maxLength);
            helpElement.textContent = `${maxLength} / ${maxLength} 자`;
        }, 1000);
    } else {
        helpElement.classList.remove('text-danger');
        input.classList.remove('is-invalid');
    }
}
function validateForms(titleInput, contentInput, suriForms) {
    // 제목 유효성 검사
    if (!titleInput.value.trim()) {
        alert('제목을 입력해주세요.');
        titleInput.focus();
        return false; // 유효성 검사 실패
    }

    // 내용 유효성 검사
    if (!contentInput.value.trim()) {
        alert('내용을 입력해주세요.');
        contentInput.focus();
        return false; // 유효성 검사 실패
    }

    // Suri 관련 유효성 검사
    for (let i = 0; i < suriForms.length; i++) {
        const suriForm = suriForms[i];
        const suriTitleInput = suriForm.querySelector(`input[name^="suri_title_"]`);
        const suriOptions = [];
        for (let j = 1; j <= 5; j++) {
            const optionInput = suriForm.querySelector(`input[name^="suri_que${j}_"]`);
            suriOptions.push(optionInput);
        }

        // Suri title 빈칸이 아닌지 확인
        if (!suriTitleInput.value.trim()) {
            alert('질문 제목은 빈칸이 될 수 없습니다.');
            suriTitleInput.focus();
            window.scrollTo(0, suriTitleInput.offsetTop);
            return; // 유효성 검사 실패 시 중단
        }

        // Suri 옵션 입력 필드가 1번부터 차례대로 값이 들어가고 빈칸이 없는지 확인
        let currentOption = true; // 현재 옵션 입력 필드에 값이 있는지 여부
        let previousOption = true; // 이전 옵션 입력 필드에 값이 있는지 여부
        for (let j = 0; j < suriOptions.length; j++) {
            const optionInput = suriOptions[j];
            const optionNumber = parseInt(optionInput.name.match(/\d+/)[0]); // input 번호 파싱
            const optionValue = optionInput.value.trim();
            currentOption = optionValue !== '';

            // 첫번째 옵션 입력 필드에 값이 없을 때
            if (optionNumber === 1 && !currentOption) {
                alert('질문의 선택지 1은 빈칸이 될 수 없습니다.');
                optionInput.focus();
                window.scrollTo(0, optionInput.offsetTop);
                return; // 유효성 검사 실패 시 중단
            }

            // 두번째 옵션 입력 필드에 값이 없을 때
            if (optionNumber === 2 && !currentOption) {
                alert('질문의 선택지 2는 빈칸이 될 수 없습니다.');
                optionInput.focus();
                window.scrollTo(0, optionInput.offsetTop);
                return; // 유효성 검사 실패 시 중단
            }


            // 현재 옵션 입력 필드에 값이 있는데 이전 옵션 입력 필드에 값이 비어있는 경우
            if (currentOption && !previousOption) {
                alert(`질문의 선택지 ${optionNumber - 1}번은(는) 빈칸이 될 수 없습니다.`);
                suriOptions[j - 1].focus();
                window.scrollTo(0, suriOptions[j - 1].offsetTop);
                return; // 유효성 검사 실패 시 중단
            }
            previousOption = currentOption;
        }
    }

    // 다른 필요한 유효성 검사 규칙을 여기에 추가

    return true; // 모든 유효성 검사 통과
}


function validateDateInput(startInput, endInput) {
    // research-startdate-input
    // research-enddate-input
    const startInputValue = startInput.value;
    const endInputValue = endInput.value;
    const startDate = new Date(startInputValue);
    const endDate = new Date(endInputValue);

    if (startDate > endDate) {
        alert('시작일은 종료일보다 빠를 수 없습니다.');
        startInput.focus();
        return false;
    }
    // date input의 value가 없을 때
    // todo: datepicker 변경 필요 -> 이건 나중에
    else if (!startInputValue ) {
        alert('설문 조사 시작 날짜를 입력해주세요.');
        startInput.focus();
        return false;
    }else if (!endInputValue ) {
        alert('설문 조사 종료 날짜를 입력해주세요.');
        endInput.focus();
        return false;
    }
    return true;
}