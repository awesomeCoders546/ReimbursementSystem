import React, { Component } from 'react'
import './Edit.css';
import axios from 'axios';

// export default class Edit extends Component {
//     render() {
//         // const wrapper = {
        //     display: 'flex',
        //     flexDirection: 'column',
        //     alignItems: 'center',
        //     justifyContent: 'center',
        //     width: '100%',
        //     padding: '50px',
            
        //   };


        // return (
            // <div id="edit" style={wrapper} class="card bg-success">
            // <div id="edit" className="card">
            //         <form>
            //             <h1>Edit Reimbursement</h1>
                        {/* <div class="form-group">
                        <label for="id">Id</label>
                        <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Enter id"/>
                        </div>
                        <div class="form-group">
                        <label for="exampleInputPassword1">Name</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Name"/>
                        </div>
                        <div class="form-group">
                        <label for="id">Image</label>
                        <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Enter Image"/>
                        </div>
                        <div class="form-group">
                        <label for="exampleInputPassword1">Category</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Category"/>
                        </div>
                        <div class="dropdown show">
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Category
                        </a>
                        
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="#">Travel</a>
                            <a class="dropdown-item" href="#">Meals</a>
                            <a class="dropdown-item" href="#">Lodging</a>
                        </div>
                        </div>
                        <br/> */}
            {/* <div class="form-group">
              <label for="id">Id</label>
              <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Enter id"/>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Food Expense</label>
              <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Enter Name"/>
            </div>
            <div class="form-group">
              <label for="id">Travel Expense</label>
              <input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="Travel Expense"/>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Other Expense</label>
              <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Other Expense"/>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Date Of Reimburse</label>
              <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Employee Id</label>
              <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Total Bill</label>
              <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Date Of Reimburse"/>
            </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>            
            </div>
        )
    }
} */}
class Edit extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id:0,
      foodexpense:0,
      travelexpense:0,
      otherexpense:0,
      dateOfReimburse:'',
      totalExpense:0,
    };
  }
  mySubmitHandler = (event) => {
    axios.post("http://localhost:8080/api/addImage",{
      id:this.state.id,
      foodexpense:this.state.foodexpense,
      travelexpense:this.state.travelexpense,
      otherexpense:this.state.otherexpense,
      dateOfReimburse:this.state.dateOfReimburse,
      totalExpense:this.state.totalExpense,
    });
  }


  
  render() {
    return (
      <div className="card" id="edit" >
        <form onSubmit={this.mySubmitHandler}>
        <div className="form-group">
         <label for="id">Enter your id:</label>
          <input type='text' name='id' id="id" class="form-control"/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Food Expense:</label>
        <input type='text' name='foodexpense' className="form-control"/>
        </div>
        
        <div className="form-group">
        <label for="id">Enter your Travel Expense:</label>
        <input type='text' name='travelexpense' className="form-control"/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Other Expense:</label>
        <input type='text' name='otherexpense' className="form-control"/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Date Of Reimburse:</label>
        <input type='text' name='dateOfReimburse' className="form-control"/>
        </div>
        <div className="form-group">
        <label for="id">Enter your Total Expense:</label>
        <input type='text' name='totalExpense' className="form-control"/>
        </div>
     
    
        <input class="btn btn-primary" type='submit' />
      </form>
    </div>
    );
  }
}

export default Edit