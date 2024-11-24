const inputs = document.querySelectorAll('.input')

function focusFunc(){
    this.parentNode.parentNode.classList.add('focus')
}

function blurFunc(){
    if(this.value == '')
        this.parentNode.parentNode.classList.remove('focus')
}

inputs.forEach(input =>{
    input.addEventListener('focus',focusFunc)
    input.addEventListener('blur',blurFunc)
})