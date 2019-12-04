package com.kodilla.testing.library;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class BookDirectoryTestSuite {

    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<Book>();
        for (int n = 1; n <= booksQuantity; n++) {
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }
    @Test
    public void testListBooksWithConditionsReturnList() {
        // Given
        LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<Book>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);
        when(libraryDatabaseMock.listBooksWithCondition("Secret"))
                .thenReturn(resultListOfBooks);

        // When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        // Then
        assertEquals(4, theListOfBooks.size());
    }

    @Test
    public void testListBooksWithConditionMoreThan20() {
            // Given
            LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            List<Book> resultListOf0Books = new ArrayList<Book>();
            List<Book> resultListOf15Books = generateListOfNBooks(15);
            List<Book> resultListOf40Books = generateListOfNBooks(40);
            when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                    .thenReturn(resultListOf15Books);
            when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks"))
                    .thenReturn(resultListOf0Books);
            when(libraryDatabaseMock.listBooksWithCondition("FortyBooks"))
                    .thenReturn(resultListOf40Books);

            // When
            List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
            List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
            List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FortyBooks");
            // Then

            assertEquals(0, theListOfBooks0.size());
            assertEquals(15, theListOfBooks15.size());
            assertEquals(0, theListOfBooks40.size());
        }


    @Test
    public void testListBooksWithConditionFragmentShorterThan3() {
        // Given
        LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf10Books = generateListOfNBooks(10);
        when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                .thenReturn(resultListOf10Books);

        // When
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");

        // Then
        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }

    @Test
    public void testBooksInHandsOfZero() {
        //Given
        LibraryUser libraryUserMock = mock(LibraryUser.class);
        LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> rentedBooks = generateListOfNBooks(0);

        when(libraryDatabaseMock.listBooksInHandsOf(any())).thenReturn(rentedBooks);

        //When
        int quantityOfBooks = libraryDatabaseMock.listBooksInHandsOf(libraryUserMock).size();
        //Than
        assertEquals(0,quantityOfBooks );
    }
    @Test
    public void testBooksInHandsOfOne() {
        //Given
        LibraryUser libraryUserMock = mock(LibraryUser.class);
        LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);

        List<Book> rentedBooks1 = generateListOfNBooks(1);

        when(libraryDatabaseMock.listBooksInHandsOf(any())).thenReturn(rentedBooks1);

        //When
        int quantityOfBooks = libraryDatabaseMock.listBooksInHandsOf(libraryUserMock).size();
        //Than
        assertEquals(1,quantityOfBooks );
    }
    @Test
    public void testBooksInHandsOfFive() {
        //Given
        LibraryUser libraryUserMock = mock(LibraryUser.class);
        LibraryDataBase libraryDatabaseMock = mock(LibraryDataBase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);

        List<Book> rentedBooks5 = generateListOfNBooks(5);
        when(libraryDatabaseMock.listBooksInHandsOf(any())).thenReturn(rentedBooks5);

        //When
        int quantityOfBooks = libraryDatabaseMock.listBooksInHandsOf(libraryUserMock).size();
        //Than
        assertEquals(5,quantityOfBooks );
    }
}

