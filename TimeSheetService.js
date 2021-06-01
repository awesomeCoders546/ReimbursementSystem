import axios from 'axios'
import { Component } from 'react'

class TimeSheetService {
    saveTimeSheet(timeSheet){
         return axios.post('http://localhost:8081/saveMultipleTimesheet',timeSheet)
     }

    getAll(superId)
    {
        return axios.get("http://localhost:8081//superWeek"+"/"+superId)
    }
    getAllAccT()
    {
        return axios.get("http://localhost:8081//accCurrentWeek")
    }
    getAllAdminT()
    {
        return axios.get("http://localhost:8081//adminCurrentWeek")
    }
    deleteTimeSheetId(id)
    {
        return axios.delete(`http://localhost:8081/deleteTimeSheet/${id}`)
    }
    getTimeSheetById(tid){
        return axios.get(`http://localhost:8081/TimeSheetbyTId/${tid}`)
    }
    getEmployeeById(eid){
        return axios.get(`http://localhost:8081/TimeSheetbyEId/${eid}`)
    }
    updateTimeSheet(timeSheet){
        return axios.put('http://localhost:8081/updateTimeSheet',timeSheet)
    }
     superWaiting(sid)
     {
         return axios.get(`http://localhost:8081/superwaiting/${sid}`)
     }
   
     superApproved(sid){
         return axios.get(`http://localhost:8081/superapproved/${sid}`)
     }
     superDisapproved(sid){
         return axios.get(`http://localhost:8081/superdisapproved/${sid}`)
     }
     allaccountantApproved(){
        return axios.get("http://localhost:8081/allaccountantapproved")
    }
    updateTimeSupervisorStatus(timeSheet){
        return axios.put("http://localhost:8081//upadteTimeSuperApproved",timeSheet)
    }
    updateTimeaccountantStatus(timeSheet){
        return axios.put("http://localhost:8081//upadteTimeAccountantApproved",timeSheet)
    }  

    allaccountantDisApproved(){
        return axios.get("http://localhost:8081/allaccountantDisapproved")
    }
    findWeekly(){
        return axios.get("http://localhost:8081/findbyWeek")
    }
    // findCodding(){
    // return axios.get("http://localhost:8081/findbycoddingTask")
    // }

    
    findCodding(task){
        return axios.get("http://localhost:8081/timeSheetByTask"+"/"+task)
    }
        
    findRequirementGathering(){
        return axios.get("http://localhost:8081/findbyrequirementgatheringTask")
    }
    findTesting(){
    return axios.get("http://localhost:8081/findbytestingTask")
    }
    findMeeting(){
    return axios.get("http://localhost:8081/findbymeetingTask")
    }
}

export default new TimeSheetService()