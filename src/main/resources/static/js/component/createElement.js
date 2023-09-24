function createETCinput(questionNumber) {
    const etcContainerHTML = `
        <div class="mb-3 row align-items-center">
            <label class="form-label col-auto">선택 사유 작성 유무:</label>
            <div class="form-check col-auto">
                <input class="form-check-input" type="radio" name="suri_etc_${questionNumber}" id="suri_etc_yes_${questionNumber}" value="Yes">
                <label class="form-check-label" for="suri_etc_yes_${questionNumber}">예</label>
            </div>
            <div class="form-check col-auto">
                <input class="form-check-input" type="radio" name="suri_etc_${questionNumber}" id="suri_etc_no_${questionNumber}" value="No" checked>
                <label class="form-check-label" for="suri_etc_no_${questionNumber}">아니오</label>
            </div>
        </div>
    `;

    const parser = new DOMParser();
    return parser.parseFromString(etcContainerHTML, 'text/html').body.firstChild;
}

function createHiddenInput(name) {
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;
    return input;
}

function createLabel(text) {
    const label = document.createElement('label');
    label.textContent = text;
    return label;
}

function createTextInput(name) {
    const input = document.createElement('input');
    input.type = 'text';
    input.classList.add('form-control');
    input.name = name;
    return input;
}

function createMultiInput(questionNumber) {
    let initialValue = 1;
    if(rsDTO.suri)initialValue = rsDTO.suri[questionNumber - 1] ? rsDTO.suri[questionNumber - 1].suri_multi : 1;
    const questionMultiLabel = '제출 가능한 선택지 개수 최대값:';
    const multiContainerHTML = `
        <div class="dropdown">
        <span class="label-text">제출 가능한 선택지 개수 최대값:</span>
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton_${questionNumber}" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span id="selectedValue_${questionNumber}">${initialValue}</span>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton_${questionNumber}">
                <div class="dropdown-item" data-value="1">1</div>
                <div class="dropdown-item" data-value="2">2</div>
                <div class="dropdown-item" data-value="3">3</div>
                <div class="dropdown-item" data-value="4">4</div>
                <div class="dropdown-item" data-value="5">5</div>
            </div>
            <input type="hidden" name="suri_multi_${questionNumber}" id="suri_multi_${questionNumber}" value="1">
        </div>
    `;

    // HTML 문자열을 DOM 요소로 파싱하여 반환
    const parser = new DOMParser();
    const multiContainer = parser.parseFromString(multiContainerHTML, 'text/html').body.firstChild;

    // 드롭다운 선택 항목 클릭 시 이벤트 처리
    const dropdownItems = multiContainer.querySelectorAll('.dropdown-item');
    dropdownItems.forEach(item => {
        item.addEventListener('click', function(event) {
            const selectedValue = event.target.getAttribute('data-value');
            document.getElementById(`selectedValue_${questionNumber}`).textContent = selectedValue;
            document.getElementById(`suri_multi_${questionNumber}`).value = selectedValue;
        });
    });

    return multiContainer;
}


function addValidationAndLimit(input, maxLength) {
    const helpElement = document.createElement('small');
    helpElement.name = input.name + '-help';
    helpElement.classList.add('form-text');
    // helpElement.classList.add('text-muted');
    // input 요소의 초기값을 이용하여 길이를 설정
    const initialLength = input.value.length;
    helpElement.textContent = `${initialLength} / ${maxLength} 자`;

    // 부모 요소가 존재할 때만 appendChild 호출
    if (input.parentNode) {
        input.parentNode.appendChild(helpElement);
    }

    input.addEventListener('input', function () {
        const inputLength = input.value.length;
        helpElement.textContent = `${inputLength} / ${maxLength} 자`;
        if (inputLength > maxLength) {
            helpElement.classList.add('text-danger');
            input.classList.add('is-invalid');
            setTimeout(function () {
                input.value = input.value.substring(0, maxLength);
                helpElement.textContent = `${maxLength} / ${maxLength} 자`;
                helpElement.classList.remove('text-danger');
                input.classList.remove('is-invalid');
            }, 1000);
        } else {
            helpElement.classList.remove('text-danger');
            input.classList.remove('is-invalid');
        }
    });
}


function addSuriForm(questionNumber) {
    // 이미 해당 번호의 Suri 폼이 존재하는지 확인
    // 절대 실행 안되지만 안전장치
    const existingForm = document.getElementById(`suri_no_${questionNumber}`);
    if (existingForm) {
        return; // 이미 존재하면 추가하지 않음
    }
    // div 요소 생성
    const suriForm = document.createElement('div');
    suriForm.classList.add('mb-3');
    suriForm.classList.add('suri-form');
    suriForm.classList.add('border');
    suriForm.classList.add('border-primary');
    suriForm.classList.add('p-5');
    suriForm.id = `suri_no_${questionNumber}`;

    // suri_seq input 생성 및 설정
    const suriSeqInput = createHiddenInput(`suri_seq_${questionNumber}`);

    // suriSeqInput.value = rsDTO.suri[questionNumber - 1].suri_seq;
    if(rsDTO.suri)suriSeqInput.value = rsDTO.suri[questionNumber - 1] ? rsDTO.suri[questionNumber - 1].suri_seq : '';
    suriForm.appendChild(suriSeqInput);


    // sur_seq input 생성 및 설정
    const surSeqInput = createHiddenInput(`sur_seq_${questionNumber}`);
    surSeqInput.value = rsDTO.sur_seq;
    suriForm.appendChild(surSeqInput);

    // suri_no input 생성 및 설정
    const suriNoInput = createHiddenInput(`suri_no_${questionNumber}`);
    suriNoInput.value = questionNumber;
    suriForm.appendChild(suriNoInput);

    // 질문 제목 input 생성 및 설정
    const questionTitleLabel = createLabel(`문항 ${questionNumber}:`);
    suriForm.appendChild(questionTitleLabel);

    const questionTitleInput = createTextInput(`suri_title_${questionNumber}`, questionNumber);
    suriForm.appendChild(questionTitleInput);
    addValidationAndLimit(questionTitleInput, 200);

    const optionContainer = document.createElement('div');
    optionContainer.classList.add('ms-5');

    // 질문 보기 (1부터 5까지) input 생성 및 설정
    for (let i = 1; i <= 5; i++) {
        const questionOptionLabel = createLabel(`선택지 ${i}:`);
        optionContainer.appendChild(questionOptionLabel);

        const questionOptionInput = createTextInput(`suri_que${i}_${questionNumber}`, questionNumber);
        optionContainer.appendChild(questionOptionInput);
        addValidationAndLimit(questionOptionInput, 100);
        // 유효성 검사와 option 구분 짓기 위한 비어있는 div 요소 추가
        const optionDiv = document.createElement('div');
        optionDiv.classList.add('mb-3');
        optionContainer.appendChild(optionDiv);

    }

    suriForm.appendChild(optionContainer);

    // 질문 multi input 생성 및 설정
    // 숫자 입력 필드 생성

    const questionMultiInput = createMultiInput(questionNumber);
    // questionMultiInput.value = 1;
    suriForm.appendChild(questionMultiInput);


    const questionETCInput = createETCinput(questionNumber);

    suriForm.appendChild(questionETCInput);

    // const etcOptionsContainer = document.createElement('div');
    //
    // // 질문 etc input 생성 및 설정
    // const etcLabel = createLabel('선택 사유 작성 유무:');
    // etcOptionsContainer.appendChild(etcLabel);
    // etcOptionsContainer.classList.add('mb-3');
    //
    // // Create radio buttons for "Yes" and "No"
    // const optionYes = createRadioOption(questionNumber, 'Yes', false);
    // const optionNo = createRadioOption(questionNumber, 'No', true);
    //
    // etcOptionsContainer.appendChild(optionYes);
    // etcOptionsContainer.appendChild(optionNo);
    //
    // suriForm.appendChild(etcOptionsContainer);


    // research-query-container 추가
    const container = document.getElementById('research-query-container');
    container.appendChild(suriForm);

    // deleteQueryId에 suri_seq 추가
    if (suriForm.querySelector('input[name^="suri_seq_"]')) {
        let suri_seq = suriForm.querySelector('input[name^="suri_seq_"]').value
        deletedQueryId = deletedQueryId.filter(_suri_seq => _suri_seq !== suri_seq)
    }

    cntSuriForm();
}
function deleteSuriForm() {
    let suriForms = document.querySelector('#research-query-container')
    if (suriForms.lastChild) {
        // console.log(suriForms.lastChild.querySelector('input[name^="suri_seq_"]').value)
        let suri_seq = suriForms.lastElementChild.querySelector('input[name^="suri_seq_"]').value
        if (suri_seq) {
            if (deletedQueryId.filter(_suri_seq => _suri_seq === suri_seq).length === 0) {
                deletedQueryId.push(suri_seq)
            }
        }
    }
    if (suriForms.lastChild) suriForms.lastChild.remove()
    console.log(deletedQueryId)
    cntSuriForm();
}


function cntSuriForm() {
    let cnt = document.querySelector('#research-query-container').childElementCount;
    document.querySelector('#num-of-questions').textContent = `총 문항 수: ${cnt}`;
}