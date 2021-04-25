import React, { Component } from 'react'
import './Add.css';
import axios from 'axios';

// const { Option } = Select;
// function handleChange(value) {
//   console.log(`selected ${value}`);
// }
// export default class Add extends Component {
   
//   render() {
//       // const wrapper = {
      //   display: 'flex',
      //   flexDirection: 'column',
      //   alignItems: 'center',
      //   justifyContent: 'center',
      //   width: '50%',
      //   padding: '50px',
      //   left:'350px',        
      // };




        // return (
            // <div className="card-container" id="Add" style={wrapper} >
      // <div className="card-container" id="Add" >

      //   <form>
      //       <h1>Add Reimbursement </h1>
      //       <div class="form-group">
      //         <label for="id">Id</label>
      //         <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Enter id"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="exampleInputPassword1">Food Expense</label>
      //         <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Name"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="id">Travel Expense</label>
      //         <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Travel Expense"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="exampleInputPassword1">Other Expense</label>
      //         <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Other Expense"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="exampleInputPassword1">Date Of Reimburse</label>
      //         <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="exampleInputPassword1">Employee Id</label>
      //         <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
      //       </div>
      //       <div class="form-group">
      //         <label for="exampleInputPassword1">Total Bill</label>
      //         <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
      //       </div>
             /* <div class="dropdown show">
              <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Category
              </a>
            
              <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <a class="dropdown-item" href="#">Travel</a>
                <a class="dropdown-item" href="#">Meals</a>
                <a class="dropdown-item" href="#">Lodging</a>
              </div> */
               /* <Select defaultValue="lucy" style={{ width: 120 }} onChange={handleChange}>
                <Option value="jack">Jack</Option>
                  <Option value="lucy">Lucy</Option>
                  <Option value="disabled" disabled>Disabled</Option>
                <Option value="Yiminghe">yiminghe</Option>
               </Select>
     */
            /* </div> */
            /* <br/>
           
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>            
        </div>
        )
    }
} */
class Add extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id:0,
      foodexpense:0,
      travelexpense:0,
      otherexpense:0,
      dateOfReimburse:'',
    };
  }
  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({[nam]: val});
  }
  mySubmitHandler = (event) => {
    event.preventDefault();
    axios.post("http://localhost:8080//reimbursementapi/all/requestReimburse",{
      employeeId:this.state.id,
      foodExpense:this.state.foodexpense,
      travelExpense:this.state.travelexpense,
      otherExpenss:this.state.otherexpense,
      dateOfReimburse:this.state.dateOfReimburse,
      
    });
  }


  
  render() {
    return (
      <div className="card-container" id="add" >
        <form onSubmit={this.mySubmitHandler}>
        <div className="form-group">
         <label for="id">Enter Employee id:</label>
          <input type='text' name='id' id="id" class="form-control" onChange={this.myChangeHandler}/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Food Expense:</label>
        <input type='text' name='foodexpense' className="form-control" onChange={this.myChangeHandler}/>
        </div>
        
        <div className="form-group">
        <label for="id">Enter your Travel Expense:</label>
        <input type='text' name='travelexpense' className="form-control"  onChange={this.myChangeHandler} />
        </div>
        <div className="form-group">
        <label for="id">Enter your Other Expense:</label>
        <input type='text' name='otherexpense' className="form-control"  onChange={this.myChangeHandler}/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Date Of Reimburse:(Format YYYY-MM-DD)</label>
        <input type='text' name='dateOfReimburse' className="form-control" onChange={this.myChangeHandler}/>
        </div>
        {/* <div className="form-group">
        <label for="id">Enter your Total Expense:</label>
        <input type='text' name='totalExpense' className="form-control"/>
        </div> */}
     
    
        <input class="btn btn-primary" type='submit' />
      </form>
    </div>
    );
  }
}

export default Add