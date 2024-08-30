import { Developer } from "./developer";
import { Comment } from "./comment";

export class Post {

    constructor(
        public title: string,
        public content: string,
        public postId?: number,
        public developer: Developer | null = null,
        public comments: Comment[] | [] = []
    )
    {

    }
   
}