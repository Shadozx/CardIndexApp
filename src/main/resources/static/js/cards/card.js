// const txHeight = 16
// const tx = document.getElementsByTagName('textarea')

// for (let i = 0; i < tx.length; i++) {
//   if (tx[i].value == '') {
//     tx[i].setAttribute('style', 'height:' + txHeight + 'px;overflow-y:hidden;')
//   } else {
//     tx[i].setAttribute(
//       'style',
//       'height:' + tx[i].scrollHeight + 'px;overflow-y:hidden;'
//     )
//   }
//   tx[i].addEventListener('input', OnInput, false)
// }

// function OnInput(e) {
//   this.style.height = 0
//   this.style.height = this.scrollHeight + 'px'
// }

document.addEventListener('DOMContentLoaded', function () {
  const tx = document.getElementsByTagName('textarea')
  for (let i = 0; i < tx.length; i++) {
    console.log(tx[i].scrollHeight)
    tx[i].setAttribute(
      'style',
      'height:' + tx[i].scrollHeight + 'px;overflow-y:hidden;'
    )
    tx[i].addEventListener('input', OnInput, false)
  }

  function OnInput() {
    this.style.height = 0
    this.style.height = this.scrollHeight + 'px'
  }
})

/**
 * <div class="card">
            <div class="card-name">Card</div>
            <div class="content">
              <div class="description">
                <div class="card-element">Description</div>
                <textarea
                  class="textarea"
                  placeholder="Enter a message..."
                ></textarea>
              </div>

              <div class="samples">
                <div class="card-element">Додатково</div>
                <textarea
                  class="textarea addition"
                  data-autoresize
                  placeholder="Enter a message..."
                ></textarea>
              </div>
            </div>
          </div>
 */
