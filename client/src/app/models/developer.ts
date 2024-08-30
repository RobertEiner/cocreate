import { Post } from "./post";



export class Developer {
    constructor(
        public userName: string,
        public emailAddress: string,
        public preferredLanguage: string,
        public id?: number,
        public posts?: Post[],
    )
    {

    }
}