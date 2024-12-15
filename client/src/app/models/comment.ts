import { Developer } from "./developer";


export class Comment {
    constructor(
        public commentId: number,
        public content: string,
        public developer: Developer,
        public createdAt?: string
    )
    {
        
    }
}