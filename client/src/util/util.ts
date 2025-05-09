import { inject } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Post } from "../app/models/post";


export class Util {

    private activeRoute: ActivatedRoute = inject(ActivatedRoute);

    // Gets the id from the URL-pararmeter
    
    getDevIdFromUrl() {
       return this.activeRoute.snapshot.paramMap.get('id');
    }

    convertDate(createdAt: string): string {
        const date = createdAt ? new Date(createdAt) : null;
        return date?.getFullYear() + '-' + date?.getMonth() + '-' + date?.getDate();
    }
}
