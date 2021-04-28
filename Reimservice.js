import axios from 'axios'
const URL='http://localhost:8082/reimbursementapi/all//accountReimburse';
class Reimservice
{
    getReimburse()
    {
         return axios.get(URL);
    }
}
export default new Reimservice();