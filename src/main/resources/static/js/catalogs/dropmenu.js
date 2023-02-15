const btns = document.getElementsByClassName('btn-dropdown')
const navs = document.getElementsByClassName('nav-dropdown')


for(let i = 0; i < btns.length; i++) {
    btns[i].addEventListener('click', () => {

      if (!btns[i].classList.contains('is-open')) {

        btns[i].classList.add('is-open')
        navs[i].classList.add('is-open')
      } else {
        btns[i].classList.remove('is-open')
        navs[i].classList.remove('is-open')
      }
    })
}
