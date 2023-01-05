window.addEventListener("load", function (){

    const turnosList = document.getElementById("listarTurnos");
    const btnRegistro = document.getElementById("registroNuevo");
    const registro = document.getElementById('turnoRegistro');
    const insertRow = document.getElementById("body-table");
    const formRegistro = document.getElementById('registro')
    const inputPaciente = document.getElementById('paciente_id');
    const inputOdontologo = document.getElementById('Odontologo_id');
    const inputFechaTurno = document.getElementById('turnoFecha')

    const update_form = document.getElementById('update_turno_form')

    const id_turno = document.getElementById('id_turno')
    const turno_paciente= document.getElementById('id_paciente')
    const turno_odontologo = document.getElementById('id_odontologo')
    const turno_fecha = document.getElementById('nuevaFecha')

    const URL_BASE= "http://localhost:8080"

   
    function listarTurnos(){
      const url = `${URL_BASE}/turnos`;
    
      fetch(url)
        .then(response => {
          return response.json()
        })
        .then(data =>{
        console.log(data);
        renderizarTurnos(data)

      }) 
    }

    function renderizarTurnos(arr){
      insertRow.innerHTML='';

      for(let i= 0 ; i< arr.length; i++){

        let get_More_Info_Btn = `<button id=btn_id_${arr[i].id} type="button" class="btn btn-dark btn_id" data-bs-toggle="modal" data-bs-target="#exampleModal">${arr[i].id}</button>`
        let tr_id = 'tr_'+ arr[i].id
        let turnoRow = `
          <tr id=${tr_id}> 
            <td>${get_More_Info_Btn} </td>
            <td class="td_first_name_paciente">${arr[i].paciente.nombre.toUpperCase()} ${arr[i].paciente.apellido.toUpperCase()}</td>
            <td class="td_first_name_odontologo">${arr[i].odontologo.nombre.toUpperCase()} ${arr[i].odontologo.apellido.toUpperCase()}</td>
            <td class="td_fechaTurno">${arr[i].fecha}</td>
            <td class="d-flex-justify-content-center"><button id="${arr[i].id}" type="submit" class="delete btn-close ps-5"></button></td>
            </tr>
          </tr>`;

      insertRow.innerHTML+= turnoRow;
      } 
    }

    turnosList.addEventListener('click', e =>{
    
      e.preventDefault();

      listarTurnos();
    })

    function registrarTurno(){

      const payload={
        paciente_id:inputPaciente.value,
        odontologo_id:inputOdontologo.value,
        fecha: inputFechaTurno.value
    }

      const settings={
        method: 'POST',
        body: JSON.stringify(payload),
        headers:{
            'Content-type':'application/json'
        }
    }
    const url = `${URL_BASE}/turnos`
  
    fetch(url,settings)
    .then(response =>{
        return response.json()
    })
    .then(data =>{

        console.log("Respuesta del post");
        console.log(data)

    }).catch(err => {

        console.log("Promesa rechazada:" + err);
    })
    }

    btnRegistro.addEventListener('click', e=>{

      e.preventDefault();

      registro.classList.remove("d-none")
    })

    formRegistro.addEventListener('submit', e=>{

      e.preventDefault();

      registrarTurno();

      formRegistro.reset();

    })

    document.addEventListener('click', e => {
      if (e.target.classList.contains("delete")) {
        borrarTurno(e.target.id);
        e.target.parentElement.parentElement.remove();
      }
    });

    function borrarTurno(id) {

      const url = `${URL_BASE}/turnos/${id}`
      
      const settings ={
        method: 'DELETE',
        headers: {
          'Content-type':'application/json'
        }
      }
      fetch(url, settings)
      .then(response => {
        console.log("Respuesta del delete");
        console.log(response);
      })
      .then(data =>{

      })
      .catch(err => {

        console.log("Promesa rechazada:" + err);
        
    })
  
    };

    function buscarPorId(){
      document.addEventListener('click', e =>{

        if(e.target.classList.contains('btn_id')){
          
          let id_of_button = (e.target.id);
          let turnoId = id_of_button.split("_")[2];
    
          const url = `${URL_BASE}/turnos/${turnoId}`;
    
          fetch(url)
            .then(response => {
              return response.json()
            })
            .then(data =>{
              console.log("Respuesta del get por id");
            console.log(data);
            id_turno.value = data.id
            turno_paciente.value = data.paciente_id
            turno_odontologo.value = data.odontologo_id
            turno_fecha.value = data.fecha
          })
          .catch(err => {

            console.log("Promesa rechazada:" + err);
            
        })
        }
      })
    }

  buscarPorId();

    function actualizarTurno(){

      update_form.addEventListener('submit', e =>{

        e.preventDefault();

          let payload = {
            id: id_turno.value,
            paciente_id: turno_paciente.value,
            odontologo_id: turno_odontologo.value,
            fecha: turno_fecha.value
        }
        const settings={
          method: 'PUT',
          body: JSON.stringify(payload),
            headers:{
              'Content-type':'application/json'
            }
        }
        const url = `${URL_BASE}/turnos`
      
        fetch(url,settings)
          .then(response =>{
            return response.json()
          })
          .then(data =>{
            listarTurnos();
          console.log("Respuesta del put");
          console.log(data)

          }).catch(err => {
           console.log("Promesa rechazada:" + err);
          })
      })
    }
    actualizarTurno();
})