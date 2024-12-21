import { inject } from "@angular/core";
import { ActivatedRoute } from "@angular/router";


export class Util {

    private activeRoute: ActivatedRoute = inject(ActivatedRoute);

    // Gets the id from the URL-pararmeter
    
    getDevIdFromUrl() {
       return this.activeRoute.snapshot.paramMap.get('id');
    }
}
