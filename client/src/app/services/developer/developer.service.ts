import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Developer } from '../../models/developer';
import { baseUrl } from '../../environment/environment';


@Injectable({
  providedIn: 'root'
})
export class DeveloperService {

  constructor() { }

  httpClient: HttpClient = inject(HttpClient);
  baseUrl: string = baseUrl;

  createDeveloper(newDeveloper: Developer): Observable<Developer>{
    // the <Developer> says that the post method is expected to return an Observable that will emit a Developer object
    console.log('SERVICE:_ ', newDeveloper.password)
    return this.httpClient.post<Developer>(`${baseUrl}/developers/`, newDeveloper);
  }

  getDeveloperById(id: number) {
    return this.httpClient.get<Developer>(`${baseUrl}/developers/${id}`);
  }

  checkDeveloperCredentials(userName: string, password: string): Observable<Developer> {
    const requestBody = {userName, password};
    return this.httpClient.post<Developer>(`${baseUrl}/developers/auth`, requestBody);
  }



}
