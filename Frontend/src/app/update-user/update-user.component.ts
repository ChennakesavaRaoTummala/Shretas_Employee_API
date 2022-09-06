import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User = new User();

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    }, error => console.log(error));

  }

  getUsers() {
    this.router.navigate(['users']);
  }

  onSubmit() {
    this.userService.updateUserById(this.id, this.user).subscribe(data => {
      alert('USER UPDATED SUCCESSFULLY');
      this.getUsers();
    },
      error => console.log(error));
  }

}
