import { ApplicationModule, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  url = process.env['BACKEND_URL'] ?? "http://localhost:8080/"; 


  async attemptLogin(username:string, password:string) : Promise<apiCallResult>{
    const data = await fetch(
      this.url+"user/login",
      {
        method: "POST",
        body: JSON.stringify({
          username: username,
          password: password
        })
      });
      if (data.status == 400){
        return {
          success:-1,
          data:"Wrong username or password"
        }
      }
      return {
        success: 1,
        data:username
      }
  }

  async attemptCreateAccount(username:string, password:string): Promise<apiCallResult>{
    const data = await fetch(
      this.url+"user/create",
      {
        method:"POST",
        body: JSON.stringify({
          username:username,
          password:password
        })
      });
    if (data.status == 400){
      return {
        success:-1,
        data: await data.text()
      }
    }
    return {
      success: 1,
      data: username
    }
  }
  constructor() { }
}
