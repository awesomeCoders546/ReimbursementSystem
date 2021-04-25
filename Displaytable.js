import React, { Component } from 'react'
import axios from 'axios';
import './Display.css';

export default class Displaytable extends Component 
{
    constructor(props){
        super(props)
        this.state={
            emp:[],
            id:0,
        }
    }
    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
      }
    // componentDidMount() {
    //     axios.get("http://localhost:8080//reimbursementapi/all/employeeReimburse/"+this.state.id)
    //       .then(res => {
    //         const emp = res.data;
    //         this.setState({ emp });
    //       })
          
    //   }
      mySubmitHandler = (event) => {
        event.preventDefault();
        axios.get("http://localhost:8080//reimbursementapi/all/employeeReimburse/"+this.state.id)
        .then(res => {
          const emp = res.data;
          this.setState({ emp });
        })
    }

    render() {
        return (
    <div className="displaycard" id="displaytable">
         <form onSubmit={this.mySubmitHandler}>
             <div className="form-group">
                <label for="id">Enter Employee id:</label>
                <input type='text' name='id' id="id" class="form-control" onChange={this.myChangeHandler}/>
            </div>
            <br/>
            <input class="btn btn-primary" id="button" type='submit'/>
        
         </form>
            <h1 className="text-center">Employee List</h1>
             <table className="table table-striped">
                <thead>
                <tr>
                    <td>employee_id</td>
                    <td>date_of_reimburse</td>
                    <td>travel_expense</td>
                    <td>food_expense</td>
                    <td>other_expense</td>
                    <td>total_expense</td>
                </tr>
                </thead>
                
                <tbody>
                {this.state.emp.map(
                                empl=>
                                <tr key={empl.id}>
                                    <td>{empl.employeeId}</td>
                                    <td>{empl.dateOfReimburse}</td>
                                    <td>{empl.travelExpense}</td>
                                    <td>{empl.foodExpense}</td>
                                    <td>{empl.otherExpenss}</td>
                                    <td>{empl.totalExpense}</td>
                                    
                                </tr>
                            )
                 }           
                </tbody>
            </table>
    </div>
        )
    }
}
