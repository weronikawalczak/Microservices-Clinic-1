export const setStoreUserDetails = userDetails => ({
   type: 'SET_USER_DETAILS',
   userDetails
});

//USEFUL FUNCTIONS

export const redirectByRole = (role, props) => {
    switch (role) {
        case "doctor":
            props.history.push("/doctor");
            return;
        case "assistant":
            props.history.push("/assistant");
            return;
        case "patient":
            props.history.push("/patient");
            return;
        default:
            return;
    }

};

// export const checkUserByLocalToken = ({setUserDetails}) => {
//    const fetchData = fetch('http://localhost:8762/auth/login', {
//       method: 'GET',
//       async: false,
//       headers: {
//          'Authorization': localStorage.token,
//       }
//    });
//
//    fetchData
//        .then(response => {
//           return response.json();
//        })
//        .then(results => {
//            setUserDetails(results.uuid)
//        });
// };

// export const getInfo = ({setUserDetails}, userUUID) => {
//     let URL = 'http://localhost:8762/doctor-mssc/doctors/' + userUUID;
//     const fetchData = fetch(URL, {
//         method: 'GET',
//         async: false,
//         headers: {
//             'Authorization': localStorage.token,
//         }
//     });
//     console.log(URL);
//
//     fetchData
//         .then(response => {
//             console.log(response);
//             return response.json();
//         })
//         .then(results => {
//             console.log(results)
//             // setUserDetails({
//             //
//             // })
//         });
// };