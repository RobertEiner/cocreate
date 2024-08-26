import { Developer } from "./developer";

export class Post {
    constructor(
        public title: string,
        public content: string,
        public developer: Developer 
    )
    {
        
    }
}