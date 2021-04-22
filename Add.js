import React, { Component } from 'react'

export default class Add extends Component {
    render() {
      const wrapper = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100%',
        padding: '50px',
        
      };




        return (
            <div id="Add" style={wrapper} class="card bg-warning">
        <form>
            <h1>Add Reimbursement </h1>
            <div class="form-group">
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
            <br/>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>            
        </div>
        )
    }
}
