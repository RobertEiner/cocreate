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
        return date?.getFullYear() + '-0' +(date?.getMonth()! + 1) + '-' + this.preceedDateWithZero(date!);
    }

    preceedDateWithZero(date: Date) {
        return date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    }
}
