import { Post } from "./post";



export class Developer {
    constructor(
        public userName: string,
        public email: string,
        public preferredLanguage: string,
        public posts: Post[],
    )
    {

    }
}