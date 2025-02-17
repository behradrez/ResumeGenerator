import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  //TODO change this to env variable
  url = "localhost:8080"; 

  async attemptLogin(username:String, password:String){
    const loginRequest = await fetch(
      this.url+"/user/login",
      {
        method: "POST",
        body: JSON.stringify({
          username:username,
          pass: password
        })
      }
    )
    return await loginRequest.json(); 
  }

  async attemptSignup(username:String, password:String){
    const signupReq = await fetch(
      this.url+"/user/create",
      {
        method:"POST",
        body: JSON.stringify({
          username:username,
          pass:password
        })
      }
    )
    return await signupReq.json();
  }

  constructor() { }
}
