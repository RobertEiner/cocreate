import { Developer } from "./developer";
import { Comment } from "./comment";

export class Post {
    constructor(
        public postId: number,
        public title: string,
        public content: string,
        public developer: Developer,
        public comments: Comment[]
    )
    {
        
    }
}