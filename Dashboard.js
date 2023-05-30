import React, {useState} from "react";
import Papa from 'papaparse';
import api from '../api/axiosConfig';


import "./dashboard.scss";

const Dashboard = () => {

  const [columnArray, setColumn] = useState([]);
  const [values, setValues] = useState([]);

  const getEmployees = async () => {
    await api.get("/employees")
    .then(function (response) {
      const valuesArray = [];

      response.data.map((d) => {
        valuesArray.push([d.name, d.email, d.phone]);
      })
  
      setValues(valuesArray);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  const handleFileUpload = async (e) => {
    setColumn(['Name', 'Email', 'Phone Number']);
    Papa.parse(e.target.files[0], {
      header: true,
      skipEmptyLines: true,
      complete: function(result) {
        const columnArray = [];
        const valuesArray = [];

        result.data.map((d) => {
          api.post('/employees', {
            name: d.Name,
            email: d.Email,
            phone: d.Phone
          })
          .catch(function (error) {
            console.log(error);
          });
        })
      }
    })

    getEmployees();
  }

  return(
    <div className="dashboard">
      <label className="dashboard__form">
        Upload CSV file
        <input
          type="file"
          name="file"
          accept=".csv"
          id="file-upload"
          onChange={handleFileUpload}
        ></input>
      </label>

      <br/>
      {columnArray ? 
      <table className="dashboard__table">
        <thead>
          <tr>
            {columnArray.map((cols, i) => (
              <th key={i}>{cols}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {values.map((v, i) => (
            <tr key={i}>
              {v.map((value, i) => (
                <td kay={i}>{value}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table> : " "}
    </div>
  );
};

export default Dashboard;