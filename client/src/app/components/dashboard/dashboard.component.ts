import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {InventoryService} from "../../services/inventory.service";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {AddNewInvComponent} from "../add-new-inv/add-new-inv.component";
import {MatDialog} from "@angular/material/dialog";
import {UpdateInvComponent} from "../update-inv/update-inv.component";
import {Observable} from "rxjs";
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone:true,
  styleUrls: ['./dashboard.component.scss'],

  imports: [MatTableModule, MatPaginatorModule, MatCardModule, MatButtonModule, MatInputModule,FormsModule, MatFormFieldModule, MatInputModule],
})
export class DashboardComponent implements OnInit,AfterViewInit {

  param: any;
  dataSet:any;
  // @ts-ignore
  ELEMENT_DATA: Inventory[];

  searchBrand:string =''
  searchBrandArray: string[] = [];

  searchType:string=''
  searchTypeArray: string[] = [];
  searchDesc:string=''

  constructor(
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private invservice:InventoryService,
    public dialog: MatDialog
  ) {
// this.getAllinventories()
    // @ts-ignore
    console.log(this.ELEMENT_DATA)
    console.log(this.dataSet)
  }

  ngOnInit(): void {

    console.log("hi")
    this.param = this.activatedRoute.snapshot.params['username']
    this.getAllinventories()
  }

  getAllinventories(){
    let resp = this.invservice.getAllinv();
    resp.subscribe(report=> {
      this.dataSource.data=report.content  as Inventory[];
      this.dataSet=report
    })
  }

  onLogout() {
    this.router.navigate(['']);
  }

  displayedColumns: string[] = ['Id', 'Brand', 'Type', 'Description','ExpiredOn','Price','Action'];

  // @ts-ignore
  dataSource = new MatTableDataSource<Inventory>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }


  onSearch() {
    console.log(this.searchBrand)
    console.log(this.searchType)
    console.log(this.searchDesc)
    this.searchBrandArray = this.searchBrand.split(',').map(value => value.trim());
    console.log(this.searchBrandArray)
    this.searchTypeArray = this.searchType.split(',').map(value => value.trim());
    console.log(this.searchTypeArray)


    let resp = this.invservice.searchInventories(this.searchBrandArray, this.searchTypeArray,this.searchDesc,0);
    resp.subscribe(responsedata=> {
      this.dataSource.data=responsedata.content as Inventory[];
      this.dataSet=responsedata.content
      // console.log(responsedata.content)
      // console.log("jfksfre")
    })
  }

  onClear() {
    this.searchBrand=''
    this.searchType=''
    this.searchDesc=''
  }

  onClickMore() {

  //   currentpagenumber
    let pgnumber : number = this.dataSet.pageable.pageNumber;
    console.log(pgnumber+1)

    if(this.searchBrand==''&&this.searchDesc==''&&this.searchType==''){
      let resp = this.invservice.getMorewithoutSearch(pgnumber+1);
      resp.subscribe(report=> {
        this.dataSource.data=report.content  as Inventory[];
        this.dataSet=report
      })
    }
    else{
      console.log("other operation")
      pgnumber=pgnumber+1
      let resp = this.invservice.searchInventories(this.searchBrandArray, this.searchTypeArray,this.searchDesc,pgnumber);
      resp.subscribe(responsedata=> {
        this.dataSource.data=responsedata.content as Inventory[];
        this.dataSet=responsedata
      })


    }

  }

  getInvdetails(id: number): Observable<any> {
    return this.invservice.getInventoryById(id);
  }

  onUpdateInv(id: number) {
    this.getInvdetails(id).subscribe(
      (data: any) => {
        console.log(data);

        const dialogRef = this.dialog.open(UpdateInvComponent, {
          data: {
            brand: data.brand,
            description: data.description,
            expired_on: data.expired_on,
            price: data.price,
            type: data.type,
          },
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
          console.log(result);

          let requestdata:any = {
              id:id,
              brand: result.brand,
              description: result.description,
              expired_on: result.expired_on,
              price: result.price,
              type: result.type,
          }

          this.invservice.updateInventory(requestdata).subscribe(
            response => {
              console.log(response)
              this.getAllinventories()
            }
          )



        });
      },
      error => {
        console.error('Error fetching inventory details:', error);
      }
    );
  }

  onDeleteInv(id:number) {
    console.log("deleted id="+id)
    this.invservice.deleteInventory(id).subscribe(
      res => {
        console.log(res)
      this.getAllinventories()}
    )
  }

  onClickAdd() {
    const dialogRef = this.dialog.open(AddNewInvComponent, {
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result)
      this.invservice.addInventory(result).subscribe(
        result => {
          console.log(result)
          this.getAllinventories()
          // this.cdr.detectChanges();
        }
      );
    });

  }
}
export interface Inventory {
  brand:string;
  description:string;
  expired_on:string;
  id:number;
  price:number;
  type:string;
}



