window.onload = function() {

	const pw_show_hide = document.querySelector('.pw_show_hide');
	const input_id = document.querySelector('input[type=text]');
	const input_pw = document.querySelector('input[type=password]');
	const id_error = document.querySelector('.id_error');
	const pw_error = document.querySelector('.pw_error');
	const snslogin = document.querySelector('.snslogin');
	const status = document.querySelector('#status').innerHTML;
	const statusbar = document.querySelector('#statusbar');
	const loginbar = document.querySelector('#loginbar');

	console.log(input_id, input_pw, id_error, pw_error);
	console.log(status, statusbar, loginbar);

	input_id.addEventListener('click', function() {
		if (input_id.value === '') {
			id_error.style.display = 'block';
		}
		input_id.addEventListener('input', function() {
			if (input_id.value !== '') {
				id_error.style.display = 'none';
			}
		}, { once: true })
	})
	input_pw.addEventListener('click', function() {
		if (input_pw.value === '') {
			pw_error.style.display = 'block';
		}
		input_pw.addEventListener('input', function() {
			if (input_pw.value !== '') {
				pw_error.style.display = 'none';
			}
		}, { once: true })
	})



	let i = true;
	pw_show_hide.addEventListener('click', function() {
		if (i === true) {
			pw_show_hide.style.backgroundPosition = '-126px 0';
			input_pw.type = 'text';
			i = false;
		} else {
			pw_show_hide.style.backgroundPosition = '-105px 0';
			input_pw.type = 'password';
			i = true;
		}
	})
	

}// onload.end