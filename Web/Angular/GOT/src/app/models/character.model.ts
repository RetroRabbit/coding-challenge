import { Book } from "./book.model";

export interface Character {
    url: string,
    name: string,
    gender: string,
    culture: string,
    born: string,
    died: string,
    titles: string[],
    aliases: string[],
    father: string,
    mother: string,
    spouse: string,
    allegiances: string[],
    books: Book[],
    povBooks: Book[],
    tvSeries: string[],
    playedBy: string[]
}
