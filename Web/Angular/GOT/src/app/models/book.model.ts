import { Character } from "./character.model";

export interface Book {
    book: Book | undefined;
    books: Book[];
    url: string,
    name: string,
    isbn: string,
    authors: string[],
    numberOfPages: number,
    publisher: string,
    country: string,
    mediaType: string,
    released: string,
    characters: Character[],
    povCharacters: Character[],
}
